import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { CommunityService } from '../../_services/community.service';
import { CommonModule } from '@angular/common';
import { LoggedinnavbarComponent } from '../loggedinnavbar/loggedinnavbar.component';

@Component({
  selector: 'app-admincommunity',
  standalone: true,
  imports: [FooterComponent, CommonModule, LoggedinnavbarComponent],
  templateUrl: './admincommunity.component.html',
  styleUrl: './admincommunity.component.css'
})
export class AdmincommunityComponent implements OnInit{
  postList: any[] = [];

  constructor(private communityService: CommunityService){}

  ngOnInit(): void {
    this.communityService.fetchPosts().then((data) => {
      this.postList = data;
    })
  }
}
