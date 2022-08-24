import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { ConfigService } from "src/app/service";
import { CottageService } from "src/app/service/cottage.service";

@Component({
    selector: "app-register-cottage",
    templateUrl: "./register-cottage.component.html",
    styleUrls: ["./register-cottage.component.css"],
})
export class RegisterCottageComponent implements OnInit {
    form: FormGroup;
    cottageProfile: any;
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
    ) {}

    ngOnInit() {
        this.form = this.formBuilder.group({
            name: ["", Validators.required],
            description: ["", Validators.required],
            rules: ["", Validators.required],
            roomNumber: ["", Validators.required],
            bedNumber: ["", Validators.required],
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

    onSubmit() {
        const cottage = {
            name: this.form.value.name,
            address: this.form.value.address,
            description: this.form.value.description,
            roomNumber: this.form.value.roomNumber,
            bedNumber: this.form.value.bedNumber,
            rules: this.form.value.rules,
            files: this.addedImages,
            longitude: this.form.value.longitude,
            latitude: this.form.value.latitude,
        };
        console.log(cottage);
        this.cottageService.registerCottage(cottage).subscribe(
            (response) => {
                this.router.navigate(["/cottage/" + response.name]);
            },
            (error) => {
                console.log("error");
            }
        );
    }
}
