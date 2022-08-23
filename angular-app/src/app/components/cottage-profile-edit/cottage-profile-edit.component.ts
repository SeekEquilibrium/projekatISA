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
    deletedImages: string[] = [];
    addedImages: any[] = [];
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

    deleteImage(image: string) {
        this.deletedImages.push(image);
        this.imageNames = this.imageNames.filter((name) => name != image);
    }

    deleteAddedImage(file) {
        this.addedImages = this.addedImages.filter((item) => file != item);
    }

    onFileSelected(event) {
        for (let i = 0; i < event.target.files.length; i++) {
            this.addedImages.push(event.target.files[i]);
        }
    }

    onSubmit() {
        const editCottage = {
            id: this.cottageProfile.id,
            name: this.form.value.name,
            address: this.cottageProfile.address,
            description: this.form.value.description,
            roomNumber: this.form.value.roomNumber,
            bedNumber: this.form.value.bedNumber,
            rules: this.form.value.rules,
            deletedImages: this.deletedImages,
            files: this.addedImages,
        };
        this.cottageService.editCottageProfile(editCottage).subscribe(
            (response) => {
                this.router.navigate(["/cottage/" + response.name]);
            },
            (error) => {
                console.log("error");
            }
        );
    }
}
