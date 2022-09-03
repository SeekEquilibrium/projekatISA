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
}
