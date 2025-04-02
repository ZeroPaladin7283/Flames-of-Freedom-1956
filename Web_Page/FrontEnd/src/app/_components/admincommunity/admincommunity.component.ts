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

  deletePost(id: number): void {
    console.log("Deleting post with ID:", id);

    if(confirm('Are you sure you want to delete this post?')) {
      this.communityService.deletePost(Number(id)).then(response => {
        console.log('Post deleted: ', response);

      }).catch(error => {
        console.error('Error occured in the deleting process: ', error);
      });
    }
  }
}
