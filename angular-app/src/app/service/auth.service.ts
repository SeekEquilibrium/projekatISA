import { Injectable } from "@angular/core";
import { HttpHeaders } from "@angular/common/http";
import { ApiService } from "./api.service";
import { UserService } from "./user.service";
import { ConfigService } from "./config.service";
import { catchError, map } from "rxjs/operators";
import { Router } from "@angular/router";
import { of } from "rxjs/internal/observable/of";
import { Observable } from "rxjs";
import { _throw } from "rxjs/observable/throw";

@Injectable()
export class AuthService {
    constructor(
        private apiService: ApiService,
        private userService: UserService,
        private config: ConfigService,
        private router: Router
    ) {}

    private access_token = null;

    login(user) {
        const loginHeaders = new HttpHeaders({
            Accept: "application/json",
            "Content-Type": "application/json",
        });
        // const body = `username=${user.username}&password=${user.password}`;
        const body = {
            username: user.username,
            password: user.password,
        };
        return this.apiService
            .post(this.config.login_url, JSON.stringify(body), loginHeaders)
            .pipe(
                map((res) => {
                    console.log("Login success");
                    this.access_token = res.accessToken;
                    localStorage.setItem("jwt", res.accessToken);
                })
            );
    }

    signup(user): Observable<any> {
        delete user.repeatPassword;
        const signupHeaders = new HttpHeaders({
            Accept: "application/json",
            "Content-Type": "application/json",
        });
        console.log(user);
        return this.apiService
            .post(this.config.signup_url, JSON.stringify(user), signupHeaders)
            .pipe(
                map(() => {
                    console.log("Sign up success");
                })
            );
    }

    editProfile(user): Observable<any> {
        delete user.repeatNewPassword;
        return this.apiService
            .post("http://localhost:8080/user/editProfile", user)
            .pipe(
                map(() => {
                    console.log("ok");
                })
            );
    }

    logout() {
        this.userService.currentUser = null;
        this.access_token = null;
        localStorage.removeItem("jwt");
        this.router.navigate(["/login"]);
    }

    tokenIsPresent() {
        return !!localStorage.getItem("jwt");
    }

    getToken() {
        return this.access_token;
    }
}
