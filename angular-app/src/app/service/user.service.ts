import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import { ConfigService } from "./config.service";
import { map } from "rxjs/operators";
import { HttpHeaders } from "@angular/common/http";
import { Subject } from "rxjs";

@Injectable({
    providedIn: "root",
})
export class UserService {
    currentUser = null;

    currentUserSubject: Subject<any> = new Subject<any>();

    constructor(
        private apiService: ApiService,
        private config: ConfigService
    ) {}

    setCurrentUserSubject(data) {
        this.currentUserSubject.next(data);
    }

    getMyInfo() {
        console.log(localStorage.getItem("jwt"));
        return this.apiService.get(this.config.whoami_url).pipe(
            map((user) => {
                this.currentUser = user;
                this.setCurrentUserSubject(user);
                return user;
            })
        );
    }

    getAll() {
        return this.apiService.get(this.config.users_url);
    }
}
