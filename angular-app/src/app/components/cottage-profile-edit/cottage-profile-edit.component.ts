import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { ConfigService } from "../../service";
import { CottageService } from "../../service/cottage.service";
import { CottageProfile } from "../../shared/cottageProfile";

@Component({
    selector: "app-cottage-profile-edit",
    templateUrl: "./cottage-profile-edit.component.html",
    styleUrls: ["./cottage-profile-edit.component.css"],
})
export class CottageProfileEditComponent implements OnInit {
    form: FormGroup;
    cottageProfile: CottageProfile;
    cottageOwner: any;
    imagePath: string;
    imageNames: string[];
    displayImage: string;

    returnUrl: string;
    panelOpenState = false;

    constructor(
        private route: ActivatedRoute,
        private cottageService: CottageService,
        private configService: ConfigService,
        private router: Router,
        private formBuilder: FormBuilder
    ) {
        this.imagePath = this.configService.image_path();
    }

    ngOnInit() {
        this.returnUrl = this.route.snapshot.queryParams["returnUrl"] || "/";
        this.cottageService
            .getCottageProfile(this.route.snapshot.paramMap.get("name"))
            .subscribe(
                (response) => {
                    this.cottageProfile = response;
                    this.cottageOwner = this.cottageProfile.cottageOwner;
                    this.imageNames = response.cottageImages.imagePaths;
                    console.log(this.cottageProfile);
                    console.log(this.imageNames);
                    if (this.imageNames != null) {
                        this.displayImage = this.imageNames[0];
                    }
                },
                (error) => {
                    this.router.navigate([this.returnUrl]);
                }
            );

        this.form = this.formBuilder.group({
            name: ["", Validators.required],
            description: [""],
            rules: [""],
            roomNumber: [Validators.min(1), Validators.required],
            bedNumber: [Validators.min(1), Validators.required],
        });
    }

    selectedImage(image: string) {
        this.displayImage = image;
    }

    onSubmit() {
        console.log(this.form);
    }
}
