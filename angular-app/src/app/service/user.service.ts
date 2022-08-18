import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import { ConfigService } from "./config.service";
import { map } from "rxjs/operators";
import { HttpHeaders } from "@angular/common/http";

@Injectable({
    providedIn: "root",
})
export class UserService {
    currentUser = null;

    constructor(
        private apiService: ApiService,
        private config: ConfigService
    ) {}

    getMyInfo() {
        console.log(localStorage.getItem("jwt"));
        return this.apiService.get(this.config.whoami_url).pipe(
            map((user) => {
                this.currentUser = user;
                console.log(user);
                return user;
            })
        );
    }

    getAll() {
        return this.apiService.get(this.config.users_url);
    }
}
