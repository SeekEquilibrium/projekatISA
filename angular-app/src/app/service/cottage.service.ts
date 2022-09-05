import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import { ConfigService } from "./config.service";
import { map } from "rxjs/operators";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: "root",
})
export class CottageService {
    constructor(
        private apiService: ApiService,
        private config: ConfigService,
        private http: HttpClient
    ) {}

    public getCottageProfile(name) {
        return this.apiService
            .get(this.config.get_cottage_profile_url(name))
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getOwnerCottages() {
        return this.apiService
            .get(this.config.cottage_url + "/ownersCottages")
            .pipe(
                map((res) => {
                    console.log(res);
                    return res;
                })
            );
    }

    public registerCottage(cottage) {
        console.log(cottage);
        console.log(cottage.files);
        let formData = new FormData();
        formData.append("name", cottage.name);
        formData.append("address", cottage.address);
        formData.append("description", cottage.description);
        formData.append("roomNumber", cottage.roomNumber);
        formData.append("bedNumber", cottage.bedNumber);
        formData.append("rules", cottage.rules);
        formData.append("longitude", cottage.longitude);
        formData.append("latitude", cottage.latitude);

        for (let i = 0; i < cottage.files.length; i++) {
            formData.append("files", cottage.files[i]);
        }
        // formData.append("files", editCottage.files);
        return this.apiService
            .post(this.config.cottage_url + "/register", formData)
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public editCottageProfile(editCottage) {
        console.log(editCottage);
        console.log(editCottage.files);
        let formData = new FormData();
        formData.append("id", editCottage.id);
        formData.append("name", editCottage.name);
        formData.append("address", editCottage.address);
        formData.append("description", editCottage.description);
        formData.append("roomNumber", editCottage.roomNumber);
        formData.append("bedNumber", editCottage.bedNumber);
        formData.append("rules", editCottage.rules);
        formData.append("deletedImages", editCottage.deletedImages);
        formData.append("longitude", editCottage.longitude);
        formData.append("latitude", editCottage.latitude);
        for (let i = 0; i < editCottage.files.length; i++) {
            formData.append("files", editCottage.files[i]);
        }
        // formData.append("files", editCottage.files);
        return this.apiService
            .put(this.config.cottage_url + "/edit", formData)
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getCottageAvailability(cottageId) {
        return this.apiService
            .get(
                this.config.appointment_url +
                    "/cottage/getCottageAvailabilityAndReservations/" +
                    cottageId
            )
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getCottageReservations(cottageId) {
        return this.apiService
            .get(
                this.config.appointment_url +
                    "/cottage/cottageReservations/" +
                    cottageId
            )
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getCottageActions(cottageId) {
        return this.apiService
            .get(
                this.config.appointment_url +
                    "/cottage/getCottageActions/" +
                    cottageId
            )
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public defineCottageAvailability(
        cottageId,
        startDate,
        endDate,
        pricePerDay,
        hasAction
    ) {
        return this.apiService
            .post(this.config.appointment_url + "/cottage/defineAvailability", {
                cottageId,
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

    public submitReport(report) {
        return this.apiService
            .post("http://localhost:8080/report/cottage/report", report)
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getAvailability(cottageId) {
        return this.apiService
            .get(
                this.config.appointment_url +
                    "/cottage/getCottageAvailability/" +
                    cottageId
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
                this.config.appointment_url + "/cottage/ownerCreateReservation",
                reservation
            )
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public getCottageStats(cottageId) {
        return this.apiService
            .get(this.config.appointment_url + "/cottage/stats/" + cottageId)
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }

    public delete(cottageId) {
        return this.apiService
            .delete(this.config.cottage_url + "/deleteCottage/" + cottageId)
            .pipe(
                map((res) => {
                    return res;
                })
            );
    }
}
