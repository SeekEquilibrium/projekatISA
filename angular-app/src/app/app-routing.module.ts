import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "./components/home/home.component";
import { LoginComponent } from "./components/login/login.component";
import { SignUpComponent } from "./components/sign-up/sign-up.component";
import { CottageProfileComponent } from "./components/cottage-profile/cottage-profile.component";
import { CottageProfileEditComponent } from "./components/cottage-profile-edit/cottage-profile-edit.component";

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
        path: "cottage/:name/edit",
        component: CottageProfileEditComponent,
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}
