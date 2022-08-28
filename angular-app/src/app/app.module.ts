import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { NoopAnimationsModule } from "@angular/platform-browser/animations";
import { CardComponent } from "./card/card.component";
import { HomeComponent } from "./components/home/home.component";
import { HeaderComponent } from "./components/header/header.component";
import { UserMenuComponent } from "./components/user-menu/user-menu.component";
import { LoginComponent } from "./components/login/login.component";
import { SignUpComponent } from "./components/sign-up/sign-up.component";

import { AngularMaterialModule } from "./angular-material/angular-material.module";

import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { ApiService } from "./service/api.service";
import { FooService } from "./service/foo.service";
import { AuthService } from "./service/auth.service";
import { UserService } from "./service/user.service";
import { ConfigService } from "./service/config.service";

import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { TokenInterceptor } from "./interceptor/TokenInterceptor";
import { CottageProfileComponent } from "./components/cottage-profile/cottage-profile.component";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { CottageProfileEditComponent } from "./components/cottage-profile-edit/cottage-profile-edit.component";
import { FlexLayoutModule } from "@angular/flex-layout";
import { CottageDefineAvailabilityComponent } from "./components/cottage-define-availability/cottage-define-availability.component";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatNativeDateModule } from "@angular/material/core";
import { CottageDefineActionsComponent } from "./components/cottage-define-actions/cottage-define-actions.component";
import { BusinessMenuComponent } from "./components/business-menu/business-menu.component";
import { CottagesListComponent } from "./components/cottages-list/cottages-list.component";
import { CottageHorizontalCardComponent } from "./components/cottage-horizontal-card/cottage-horizontal-card.component";
import { AuthGuardService } from "./service/auth-guard.service";
import { CottageService } from "./service/cottage.service";
import { RegisterCottageComponent } from "./components/register-cottage/register-cottage.component";
import { CottagesReservationsComponent } from "./components/cottages-reservations/cottages-reservations.component";
import { BasicUserInfoComponent } from "./components/basic-user-info/basic-user-info.component";
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
@NgModule({
    declarations: [
        AppComponent,
        CardComponent,
        HomeComponent,
        HeaderComponent,
        UserMenuComponent,
        LoginComponent,
        SignUpComponent,
        CottageProfileComponent,
        CottageProfileEditComponent,
        CottageDefineAvailabilityComponent,
        CottageDefineActionsComponent,
        BusinessMenuComponent,
        CottagesListComponent,
        CottageHorizontalCardComponent,
        RegisterCottageComponent,
        CottagesReservationsComponent,
        BasicUserInfoComponent,
        EditProfileComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        NoopAnimationsModule,
        AngularMaterialModule,
        FormsModule,
        ReactiveFormsModule,
        NgbModule,
        FlexLayoutModule,
        MatDatepickerModule,
        MatNativeDateModule,
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: TokenInterceptor,
            multi: true,
        },
        FooService,
        AuthService,
        ApiService,
        UserService,
        ConfigService,
        AuthGuardService,
        CottageService,
    ],
    bootstrap: [AppComponent],
    entryComponents: [
        CottageDefineAvailabilityComponent,
        CottageDefineActionsComponent,
        BasicUserInfoComponent,
    ],
})
export class AppModule {}
