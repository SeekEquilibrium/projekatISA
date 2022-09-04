import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { NgbCarouselConfig } from "@ng-bootstrap/ng-bootstrap";
import { ConfigService, UserService } from "../../service";
import { CottageService } from "../../service/cottage.service";
import { CottageProfile } from "../../shared/cottageProfile";
import { MatDialog } from "@angular/material/dialog";
import { BoatService } from "src/app/service/boat.service";
import { BoatDefineAvailabilityComponent } from "../boat-define-availability/boat-define-availability.component";
import { BoatDefineActionsComponent } from "../boat-define-actions/boat-define-actions.component";

@Component({
    selector: "app-boat-profile",
    templateUrl: "./boat-profile.component.html",
    styleUrls: ["./boat-profile.component.css"],
})
export class BoatProfileComponent implements OnInit {
    boatProfile: any;
    boatOwner: any;
    imagePath: string;
    returnUrl: string;
    panelOpenState = false;

    constructor(
        private route: ActivatedRoute,
        private boatService: BoatService,
        private configService: ConfigService,
        private router: Router,
        private userService: UserService,
        config: NgbCarouselConfig,
        public dialog: MatDialog
    ) {
        config.interval = 4000;
        config.keyboard = true;
        config.pauseOnHover = true;
    }

    ngOnInit() {
        this.boatService
            .getBoatProfile(this.route.snapshot.paramMap.get("name"))
            .subscribe(
                (response) => {
                    this.boatProfile = response;
                    this.boatOwner = response.boatOwner;
                    console.log(this.boatOwner);
                },
                (error) => {
                    this.router.navigate(["/"]);
                }
            );
    }
    isMyBoat() {
        if (this.userService.currentUser == undefined) {
            return false;
        }
        return this.boatOwner.username == this.userService.currentUser.username;
    }

    openAvailabilityDialog() {
        this.dialog.open(BoatDefineAvailabilityComponent, {
            data: { boatId: this.boatProfile.id },
        });
    }

    openActionsDialog() {
        this.dialog.open(BoatDefineActionsComponent, {
            data: { boatId: this.boatProfile.id },
        });
    }
}
