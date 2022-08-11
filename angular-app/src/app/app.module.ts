import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { NoopAnimationsModule } from "@angular/platform-browser/animations";
import { CardComponent } from "./card/card.component";
import { HomeComponent } from "./components/home/home.component";
import { HeaderComponent } from "./components/header/header.component";
import { UserMenuComponent } from "./user-menu/user-menu.component";
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
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}
