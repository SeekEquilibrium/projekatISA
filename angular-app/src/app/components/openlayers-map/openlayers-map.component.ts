import { Component, Input, OnInit, Output, EventEmitter } from "@angular/core";
import Map from "ol/Map";
import View from "ol/View";
import Tile from "ol/layer/Tile";
import OSM from "ol/source/OSM";
import { fromLonLat, Projection, transform } from "ol/proj";
import VectorLayer from "ol/layer/Vector";
import VectorSource from "ol/source/Vector";
import Feature from "ol/Feature";
import { Point } from "ol/geom";

@Component({
    selector: "app-openlayers-map",
    templateUrl: "./openlayers-map.component.html",
    styleUrls: ["./openlayers-map.component.css"],
})
export class OpenlayersMapComponent implements OnInit {
    @Input() lon;
    @Input() lat;
    @Input() readonly;
    @Output() latitude = new EventEmitter<any>();
    @Output() longitude = new EventEmitter<any>();

    map;
    // lat = 45.2063777680445;
    // lon = 19.809699206259737;
    constructor() {}

    ngOnInit() {
        this.initializeMap();
    }

    initializeMap() {
        if (!this.lat || !this.lon) {
            this.lon = 0;
            this.lat = 0;
        }
        const markers = new VectorLayer({
            source: new VectorSource(),
        });
        this.map = new Map({
            target: "map",
            layers: [
                new Tile({
                    source: new OSM(),
                }),
            ],
            view: new View({
                center: fromLonLat([this.lon, this.lat]),
                zoom: 14,
                maxZoom: 18,
                minZoom: 10,
            }),
        });
        this.map.addLayer(markers);

        if (this.readonly == false) {
            this.map.on("click", (e) => {
                let lonlat = transform(e.coordinate, "EPSG:3857", "EPSG:4326");
                console.log(lonlat);
                this.lat = lonlat[1];
                this.lon = lonlat[0];
                this.latitude.emit(this.lat);
                this.longitude.emit(this.lon);
                console.log(this.lat, this.lon);
                const marker = new Feature(
                    new Point(fromLonLat([this.lon, this.lat]))
                );
                markers.getSource().refresh();
                markers.getSource().addFeature(marker);
            });
        } else {
            const marker = new Feature(
                new Point(fromLonLat([this.lon, this.lat]))
            );
            markers.getSource().addFeature(marker);
        }
    }
}
