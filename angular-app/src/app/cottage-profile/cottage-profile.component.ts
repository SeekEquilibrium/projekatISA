import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CottageService } from '../service/cottage.service';

@Component({
  selector: 'app-cottage-profile',
  templateUrl: './cottage-profile.component.html',
  styleUrls: ['./cottage-profile.component.css']
})
export class CottageProfileComponent implements OnInit {

  constructor(
    private route:ActivatedRoute,
    private cottageService: CottageService
    ) { }

  ngOnInit() {
    this.cottageService.getCottageProfile(this.route.snapshot.paramMap.get('name')).subscribe(
      response => {
        console.log(response)
      }
    )
    console.log(typeof(this.route.snapshot.paramMap.get('name')))
  }

}
