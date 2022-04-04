import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConfigService } from '../service';
import { CottageService } from '../service/cottage.service';
import { CottageProfile } from '../shared/cottageProfile';

@Component({
  selector: 'app-cottage-profile',
  templateUrl: './cottage-profile.component.html',
  styleUrls: ['./cottage-profile.component.css']
})
export class CottageProfileComponent implements OnInit {
  
  cottageProfile: CottageProfile;
  cottageOwner: any;
  imagePath: string;

  constructor(
    private route:ActivatedRoute,
    private cottageService: CottageService,
    private configService: ConfigService
    ) { }

  ngOnInit() {
    this.imagePath = this.configService.image_path()
    this.cottageService.getCottageProfile(this.route.snapshot.paramMap.get('name')).subscribe(
      response => {
        this.cottageProfile = response;
        this.cottageOwner = this.cottageProfile.cottageOwner;
        console.log(this.cottageProfile)
      }
    )
  }

}
