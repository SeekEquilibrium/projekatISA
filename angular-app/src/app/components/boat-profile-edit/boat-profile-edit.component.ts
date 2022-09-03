import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { ConfigService, UserService } from "src/app/service";
import { BoatService } from "src/app/service/boat.service";

@Component({
    selector: "app-boat-profile-edit",
    templateUrl: "./boat-profile-edit.component.html",
    styleUrls: ["./boat-profile-edit.component.css"],
})
export class BoatProfileEditComponent implements OnInit {
    form: FormGroup;
    boatProfile: any;
    boatOwner: any;
    imagePath: string;
    imageNames: string[];
    displayImage: string;
    deletedImages: string[] = [];
    addedImages: any[] = [];
    returnUrl: string;
    panelOpenState = false;

    constructor(
        private route: ActivatedRoute,
        private boatService: BoatService,
        private configService: ConfigService,
        private router: Router,
        private formBuilder: FormBuilder,
        private userService: UserService
    ) {}

    ngOnInit() {
        this.boatService
            .getBoatProfile(this.route.snapshot.paramMap.get("name"))
            .subscribe(
                (response) => {
                    this.boatProfile = response;
                    this.boatOwner = this.boatProfile.cottageOwner;
                    this.imageNames = response.boatImages.imagePaths;
                    if (!this.isMyBoat()) {
                        this.router.navigate(["/"]);
                    }
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
            description: ["", Validators.required],
            rules: ["", Validators.required],
            address: ["", Validators.required],
            longitude: ["", Validators.required],
            latitude: ["", Validators.required],
        });
    }

    isMyBoat() {
        // if (this.userService.currentUser == undefined) {
        //     return false;
        // }
        return this.boatOwner.username == this.userService.currentUser.username;
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

    setLongitude(lon) {
        this.boatProfile.longitude = lon;
    }

    setLatitude(lat) {
        this.boatProfile.latitude = lat;
    }

    onSubmit() {
        const editBoat = {
            id: this.boatProfile.id,
            name: this.form.value.name,
            address: this.form.value.address,
            description: this.form.value.description,
            rules: this.form.value.rules,
            deletedImages: this.deletedImages,
            files: this.addedImages,
            longitude: this.form.value.longitude,
            latitude: this.form.value.latitude,
        };
        this.boatService.editBoatProfile(editBoat).subscribe(
            (response) => {
                this.router.navigate(["/boat/" + response.name]);
            },
            (error) => {
                console.log("error");
            }
        );
    }
}
