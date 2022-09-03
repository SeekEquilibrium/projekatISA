import { NgModule, OnInit } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "./components/home/home.component";
import { LoginComponent } from "./components/login/login.component";
import { SignUpComponent } from "./components/sign-up/sign-up.component";
import { CottageProfileComponent } from "./components/cottage-profile/cottage-profile.component";
import { CottageProfileEditComponent } from "./components/cottage-profile-edit/cottage-profile-edit.component";
import { Observable } from "rxjs";
import { UserService } from "./service";
import { AuthGuardService } from "./service/auth-guard.service";
import { CottagesListComponent } from "./components/cottages-list/cottages-list.component";
import { RegisterCottageComponent } from "./components/register-cottage/register-cottage.component";
import { CottagesReservationsComponent } from "./components/cottages-reservations/cottages-reservations.component";
import { EditProfileComponent } from "./components/edit-profile/edit-profile.component";
import { BoatProfileComponent } from "./components/boat-profile/boat-profile.component";
import { BoatProfileEditComponent } from "./components/boat-profile-edit/boat-profile-edit.component";
import { RegisterBoatComponent } from "./components/register-boat/register-boat.component";

const routes: Routes = [
    {
        path: "",
        component: HomeComponent,
        pathMatch: "full",
    },
    {
        path: "login",
        component: LoginComponent,
    },
    {
        path: "signup",
        component: SignUpComponent,
    },
    {
        path: "cottage/:name",
        component: CottageProfileComponent,
    },
    {
        path: "boat/:name",
        component: BoatProfileComponent,
    },
    {
        path: "cottage/:name/edit",
        component: CottageProfileEditComponent,
        canActivate: [AuthGuardService],
    },
    {
        path: "boat/:name/edit",
        component: BoatProfileEditComponent,
        canActivate: [AuthGuardService],
    },
    {
        path: "edit-profile",
        component: EditProfileComponent,
        canActivate: [AuthGuardService],
    },
    {
        path: "my-cottages",
        component: CottagesListComponent,
        canActivate: [AuthGuardService],
    },
    {
        path: "register/cottage",
        component: RegisterCottageComponent,
        canActivate: [AuthGuardService],
    },
    {
        path: "register/boat",
        component: RegisterBoatComponent,
        canActivate: [AuthGuardService],
    },
    {
        path: "reservations/:cottageId",
        component: CottagesReservationsComponent,
        canActivate: [AuthGuardService],
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule implements OnInit {
    currentUser: Observable<any>;
    constructor(private userService: UserService) {
        this.userService.currentUserSubject.subscribe((value) => {
            this.currentUser = value;
        });
    }
    ngOnInit() {}
}
