import { Component, OnInit } from '@angular/core';
import { AdminnavbarComponent } from '../adminnavbar/adminnavbar.component';
import { FooterComponent } from '../footer/footer.component';
import { DevLogsService } from '../../_services/dev-logs.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admindevlog',
  standalone: true,
  imports: [AdminnavbarComponent, FooterComponent, CommonModule],
  templateUrl: './admindevlog.component.html',
  styleUrl: './admindevlog.component.css'
})
export class AdmindevlogComponent implements OnInit{
  logList: any[] = [];
  gitUsers: { [key: number]: any } = {};

  constructor(private devLogsService: DevLogsService) {}

  ngOnInit(): void {
    this.devLogsService.fetchLogsData().then((data) => {
      this.logList = data;

      this.loadGitUsers();
    });
  }

  loadGitUsers(): void {
    const userIds = Array.from(new Set(this.logList.map(log => log.userId)));
    userIds.forEach((userId) => {
      this.devLogsService.fetchGitUser(userId).then((userData) => {
        this.gitUsers[userId] = userData;
      });
    });
  }
}
