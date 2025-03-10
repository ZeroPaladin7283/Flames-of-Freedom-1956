import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";
import { CommonModule } from '@angular/common';
import { CommunityService } from '../../_services/community.service';

@Component({
  selector: 'app-community',
  standalone: true,
  imports: [NavbarComponent, FooterComponent, CommonModule],
  templateUrl: './community.component.html',
  styleUrl: './community.component.css'
})
export class CommunityComponent implements OnInit{
  postList: any[] = [];

  constructor(private communityService: CommunityService){}

  ngOnInit(): void {
    this.communityService.fetchPosts().then((data) => {
      this.postList = data;
    })
  }
}
