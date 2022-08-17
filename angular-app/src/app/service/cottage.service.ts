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

    public editCottageProfile(cottage) {
        return this.apiService
            .put(this.config.cottage_url + "/edit", cottage)
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
                    "/cottage/getCottageAvailability/" +
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
        pricePerDay
    ) {
        const hasAction = false;
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
}
