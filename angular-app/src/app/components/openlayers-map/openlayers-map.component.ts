import { Component, Input, OnInit } from "@angular/core";
import Map from "ol/Map";
import View from "ol/View";
import Tile from "ol/layer/Tile";
import OSM from "ol/source/OSM";
import { fromLonLat, Projection } from "ol/proj";
import VectorLayer from "ol/layer/Vector";
import VectorSource from "ol/source/Vector";
import { Feature } from "ol";
import { Point } from "ol/geom";

@Component({
    selector: "app-openlayers-map",
    templateUrl: "./openlayers-map.component.html",
    styleUrls: ["./openlayers-map.component.css"],
})
export class OpenlayersMapComponent implements OnInit {
    @Input() lon;
    @Input() lat;
    map;
    // lat = 45.2063777680445;
    // lon = 19.809699206259737;
    constructor() {}

    ngOnInit() {
        this.initializeMap();
    }

    initializeMap() {
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
        const marker = new Feature(new Point(fromLonLat([this.lon, this.lat])));
        markers.getSource().addFeature(marker);
    }
}
