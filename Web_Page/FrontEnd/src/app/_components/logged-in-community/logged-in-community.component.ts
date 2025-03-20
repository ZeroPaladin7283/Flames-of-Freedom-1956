import { Component, OnInit } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FooterComponent } from '../footer/footer.component';
import { CommonModule } from '@angular/common';
import { CommunityService } from '../../_services/community.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-logged-in-community',
  standalone: true,
  imports: [LoggedinnavbarComponent, FooterComponent, CommonModule, RouterModule],
  templateUrl: './logged-in-community.component.html',
  styleUrl: './logged-in-community.component.css'
})
export class LoggedInCommunityComponent implements OnInit{
  postList: any[] = [];

  constructor(private communityService: CommunityService){}

  ngOnInit(): void {
    this.communityService.fetchPosts().then((data) => {
      this.postList = data;
    })
  }
}
