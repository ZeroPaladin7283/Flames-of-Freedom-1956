import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { CommunityService } from '../../_services/community.service';
import { CommonModule } from '@angular/common';
import { LoggedinnavbarComponent } from '../loggedinnavbar/loggedinnavbar.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admincommunity',
  standalone: true,
  imports: [FooterComponent, CommonModule, LoggedinnavbarComponent, FormsModule],
  templateUrl: './admincommunity.component.html',
  styleUrl: './admincommunity.component.css'
})
export class AdmincommunityComponent implements OnInit{
  postList: any[] = [];
  categories: any[] = [];
  newPost = { title: '', content: '', categoryId: 0, userId: 0 };
  showModal = false;
  showAllPosts = false;

  constructor(private communityService: CommunityService){}

  ngOnInit(): void {
    this.communityService.fetchPosts().then((data) => {
      this.postList = data;
    });

    this.communityService.fetchCategories().then((categories) => {
      this.categories = categories;
    });

    const userId = localStorage.getItem('userId');
    if (userId) {
      this.newPost.userId = parseInt(userId, 10);
    } else {
      console.error('No userId found in localStorage');
    }
  }

  openCreatePostModal() {
    this.showModal = true;
  }

  closeCreatePostModal() {
    this.showModal = false;
  }

  submitPost() {
    if (this.newPost.userId == null) {
      console.error('User ID is not available');
      return;
    }

    this.communityService.createPost(
      this.newPost.title, 
      this.newPost.categoryId, 
      this.newPost.content, 
      this.newPost.userId
    ).then(response => {
      console.log("Post created successfully", response);
      window.location.reload();
      this.closeCreatePostModal();
    }).catch(error => {
      console.error("Error creating post", error);
    });
  }

  isFormValid(): boolean {
    return (
      this.newPost.title.trim().length > 0 &&
      this.newPost.content.trim().length > 0 &&
      this.newPost.categoryId > 0
    );
  }

  togglePosts() {
    this.showAllPosts = !this.showAllPosts;
  }

  deletePost(id: number): void {
    console.log("Deleting post with ID:", id);

    if(confirm('Are you sure you want to delete this post?')) {
      this.communityService.deletePost(Number(id)).then(response => {
        console.log('Post deleted: ', response);
        window.location.reload();
      }).catch(error => {
        console.error('Error occured in the deleting process: ', error);
      });
    }
  }
}
