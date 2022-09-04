import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import { ConfigService } from "./config.service";
import { map } from "rxjs/operators";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: "root",
})
export class BoatService {
    constructor(
        private apiService: ApiService,
        private config: ConfigService,
        private http: HttpClient
    ) {}

    public getBoatProfile(name) {
        return this.apiService.get("http://localhost:8080/boat/" + name).pipe(
            map((res) => {
                return res;
            })
        );
    }

    public registerBoat(boat) {
        console.log(boat);
        console.log(boat.files);
        let formData = new FormData();
        formData.append("name", boat.name);
        formData.append("address", boat.address);
        formData.append("description", boat.description);
        formData.append("rules", boat.rules);
        formData.append("longitude", boat.longitude);
        formData.append("latitude", boat.latitude);

        for (let i = 0; i < boat.files.length; i++) {
            formData.append("files", boat.files[i]);
        }
        // formData.append("files", editCottage.files);
        return this.apiService
            .post("http://localhost:8080/boat/register", formData)
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public editBoatProfile(editBoat) {
        console.log(editBoat);
        console.log(editBoat.files);
        let formData = new FormData();
        formData.append("id", editBoat.id);
        formData.append("name", editBoat.name);
        formData.append("address", editBoat.address);
        formData.append("description", editBoat.description);
        formData.append("rules", editBoat.rules);
        formData.append("deletedImages", editBoat.deletedImages);
        formData.append("longitude", editBoat.longitude);
        formData.append("latitude", editBoat.latitude);
        for (let i = 0; i < editBoat.files.length; i++) {
            formData.append("files", editBoat.files[i]);
        }
        // formData.append("files", editCottage.files);
        return this.apiService
            .put("http://localhost:8080/boat/edit", formData)
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getOwnerBoats() {
        return this.apiService
            .get("http://localhost:8080/boat/ownerBoats")
            .pipe(
                map((res) => {
                    console.log(res);
                    return res;
                })
            );
    }

    public getBoatAvailability(boatId) {
        return this.apiService
            .get(
                this.config.appointment_url +
                    "/boat/getBoatAvailabilityAndReservations/" +
                    boatId
            )
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getBoatReservations(boatId) {
        return this.apiService
            .get(
                this.config.appointment_url + "/boat/boatReservations/" + boatId
            )
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getBoatActions(boatId) {
        return this.apiService
            .get(this.config.appointment_url + "/boat/getBoatActions/" + boatId)
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public defineBoatAvailability(
        boatId,
        startDate,
        endDate,
        pricePerDay,
        hasAction
    ) {
        return this.apiService
            .post(this.config.appointment_url + "/boat/defineAvailability", {
                boatId: boatId,
                startDate,
                endDate,
                pricePerDay,
                hasAction,
            })
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getAvailability(boatId) {
        return this.apiService
            .get(
                this.config.appointment_url +
                    "/boat/getBoatAvailability/" +
                    boatId
            )
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public createReservationForClient(reservation) {
        return this.apiService
            .post(
                this.config.appointment_url + "/boat/ownerCreateReservation",
                reservation
            )
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getBoatStats(boatId) {
        return this.apiService
            .get(this.config.appointment_url + "/boat/stats/" + boatId)
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public submitReport(report) {
        return this.apiService
            .post("http://localhost:8080/report/boat/report", report)
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }
}
