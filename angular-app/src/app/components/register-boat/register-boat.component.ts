import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { ConfigService } from "src/app/service";
import { BoatService } from "src/app/service/boat.service";

@Component({
    selector: "app-register-boat",
    templateUrl: "./register-boat.component.html",
    styleUrls: ["./register-boat.component.css"],
})
export class RegisterBoatComponent implements OnInit {
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
        private formBuilder: FormBuilder
    ) {}

    ngOnInit() {
        this.form = this.formBuilder.group({
            name: ["", Validators.required],
            description: ["", Validators.required],
            rules: ["", Validators.required],
            address: ["", Validators.required],
            longitude: ["", Validators.required],
            latitude: ["", Validators.required],
        });
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
        console.log(lon);
        this.form.controls.longitude.setValue(lon);
    }

    setLatitude(lat) {
        this.form.controls.latitude.setValue(lat);
    }

    onSubmit() {
        const boat = {
            name: this.form.value.name,
            address: this.form.value.address,
            description: this.form.value.description,
            rules: this.form.value.rules,
            files: this.addedImages,
            longitude: this.form.value.longitude,
            latitude: this.form.value.latitude,
        };
        console.log(boat);
        this.boatService.registerBoat(boat).subscribe(
            (response) => {
                this.router.navigate(["/boat/" + response.name]);
            },
            (error) => {
                console.log("error");
            }
        );
    }
}
