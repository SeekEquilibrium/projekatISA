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
}
