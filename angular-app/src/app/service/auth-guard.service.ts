import { Injectable } from "@angular/core";
import {
    ActivatedRouteSnapshot,
    CanActivate,
    Router,
    RouterStateSnapshot,
} from "@angular/router";
import { Observable } from "rxjs";
import { UserService } from "./user.service";

@Injectable()
export class AuthGuardService implements CanActivate {
    isLoggedIn: boolean = false;

    constructor(private router: Router, private userService: UserService) {}

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<boolean> {
        return new Observable<boolean>((obs) => {
            this.userService.currentUserSubject.subscribe((value) => {
                console.log(value);
                this.isLoggedIn = !!value;
                if (this.isLoggedIn) {
                    obs.next(true);
                } else {
                    alert("Please log in");
                    this.router.navigate([""]);
                    obs.next(false);
                }
            });
        });
    }
}
