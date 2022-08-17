import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { NgbCarouselConfig } from "@ng-bootstrap/ng-bootstrap";
import { ConfigService, UserService } from "../../service";
import { CottageService } from "../../service/cottage.service";
import { CottageProfile } from "../../shared/cottageProfile";
import { MatDialog } from "@angular/material/dialog";

import { CottageDefineAvailabilityComponent } from "../cottage-define-availability/cottage-define-availability.component";
@Component({
    selector: "app-cottage-profile",
    templateUrl: "./cottage-profile.component.html",
    styleUrls: ["./cottage-profile.component.css"],
    providers: [NgbCarouselConfig],
})
export class CottageProfileComponent implements OnInit {
    cottageProfile: CottageProfile;
    cottageOwner: any;
    imagePath: string;
    returnUrl: string;
    panelOpenState = false;

    constructor(
        private route: ActivatedRoute,
        private cottageService: CottageService,
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
        this.returnUrl = this.route.snapshot.queryParams["returnUrl"] || "/";
        this.imagePath = this.configService.image_path();

        this.cottageService
            .getCottageProfile(this.route.snapshot.paramMap.get("name"))
            .subscribe(
                (response) => {
                    this.cottageProfile = response;
                    this.cottageOwner = this.cottageProfile.cottageOwner;
                    console.log(this.cottageProfile);
                },
                (error) => {
                    this.router.navigate([this.returnUrl]);
                }
            );
    }

    isMyCottage() {
        if (this.userService.currentUser == undefined) {
            return false;
        }
        return (
            this.cottageOwner.username == this.userService.currentUser.username
        );
    }

    openAvailabilityDialog() {
        const dialogRef = this.dialog.open(CottageDefineAvailabilityComponent, {
            data: { cottageId: 1 },
        });
    }
}
