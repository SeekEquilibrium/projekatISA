import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CottageService } from '../service/cottage.service';
import { CottageProfile } from '../shared/cottageProfile';

@Component({
  selector: 'app-cottage-profile',
  templateUrl: './cottage-profile.component.html',
  styleUrls: ['./cottage-profile.component.css']
})
export class CottageProfileComponent implements OnInit {
  
  cottageProfile: CottageProfile;
  
  constructor(
    private route:ActivatedRoute,
    private cottageService: CottageService
    ) { }

  ngOnInit() {
    this.cottageService.getCottageProfile(this.route.snapshot.paramMap.get('name')).subscribe(
      response => {
        this.cottageProfile = response;
        // console.log(this.cottageProfile.cottageOwner)
      }
    )
  }

}
