import { Component, OnInit } from '@angular/core';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FooterComponent } from '../footer/footer.component';
import { CommonModule } from '@angular/common';
import { CommunityService } from '../../_services/community.service';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-logged-in-community',
  standalone: true,
  imports: [LoggedinnavbarComponent, FooterComponent, CommonModule, RouterModule, FormsModule],
  templateUrl: './logged-in-community.component.html',
  styleUrl: './logged-in-community.component.css'
})
export class LoggedInCommunityComponent implements OnInit{
  postList: any[] = [];
  categories: any[] = [];
  newPost = { title: '', content: '', categoryId: 0, userId: 0 };
  showModal = false;
  showAllPosts = false;

  constructor(private communityService: CommunityService) {}

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
}
