import { Injectable } from "@angular/core";
import {
    ActivatedRouteSnapshot,
    CanActivate,
    CanDeactivate,
    Router,
    RouterStateSnapshot,
} from "@angular/router";
import { Observable } from "rxjs";
import { UserService } from "./user.service";

@Injectable()
export class AuthGuardService implements CanActivate {
    constructor(private router: Router, private userService: UserService) {}

    // canActivate(
    //     route: ActivatedRouteSnapshot,
    //     state: RouterStateSnapshot
    // ): boolean {
    //     this.userService.currentUserSubject.subscribe((value) => {
    //         console.log(value);
    //         this.currentUser = value;
    //     });
    //     console.log(this.currentUser);
    //     if (!!this.currentUser) {
    //         return true;
    //     } else {
    //         alert("Please log in");
    //         this.router.navigate(["/login"]);
    //         return false;
    //     }
    // }

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<boolean> {
        return new Observable<boolean>((obs) => {
            if (!!localStorage.getItem("jwt")) {
                obs.next(true);
            } else {
                alert("Please log in");
                obs.next(false);
                this.router.navigate(["/login"]);
            }
        });
    }
}
