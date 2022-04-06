import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfigService } from '../service';
import { CottageService } from '../service/cottage.service';
import { CottageProfile } from '../shared/cottageProfile';

@Component({
  selector: 'app-cottage-profile-edit',
  templateUrl: './cottage-profile-edit.component.html',
  styleUrls: ['./cottage-profile-edit.component.css']
})
export class CottageProfileEditComponent implements OnInit {
  form: FormGroup;
  cottageProfile: CottageProfile;
  cottageOwner: any;
  imagePath: string;
  returnUrl: string;
  panelOpenState = false;

  constructor(
    private route:ActivatedRoute,
    private cottageService: CottageService,
    private configService: ConfigService,
    private router: Router,
    private formBuilder: FormBuilder,
    ) { 
    
  }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.imagePath = this.configService.image_path()
    this.cottageService.getCottageProfile(this.route.snapshot.paramMap.get('name')).subscribe(
      response => {
        this.cottageProfile = response;
        this.cottageOwner = this.cottageProfile.cottageOwner;
        console.log(this.cottageProfile)
      },
      error => {
        this.router.navigate([this.returnUrl]);
      }
    )

    this.form = this.formBuilder.group({
      name:[''],
      description:[''],
      rules:[''],
    });

  }

}
