import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import { ConfigService } from "./config.service";
import { catchError, map } from "rxjs/operators";
import { HttpHeaders } from "@angular/common/http";
import { BehaviorSubject, Observable, Subject } from "rxjs";

@Injectable({
    providedIn: "root",
})
export class UserService {
    currentUser = null;

    currentUserSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

    constructor(
        private apiService: ApiService,
        private config: ConfigService
    ) {}

    setCurrentUserSubject(data) {
        this.currentUserSubject.next(data);
    }

    getMyInfo(): Observable<any> {
        return this.apiService.get(this.config.whoami_url).pipe(
            map((user) => {
                this.currentUser = user;
                this.setCurrentUserSubject(user);
                return user;
            }),
            catchError((err) => {
                localStorage.removeItem("jwt");
                return err;
            })
        );
    }

    getAll() {
        return this.apiService.get(this.config.users_url);
    }
}
