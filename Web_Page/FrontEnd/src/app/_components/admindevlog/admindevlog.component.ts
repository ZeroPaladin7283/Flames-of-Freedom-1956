import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { DevLogsService } from '../../_services/dev-logs.service';
import { CommonModule } from '@angular/common';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";

@Component({
  selector: 'app-admindevlog',
  standalone: true,
  imports: [FooterComponent, CommonModule, LoggedinnavbarComponent],
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
    const adminIds = Array.from(new Set(this.logList.map(log => log.adminId)));
    adminIds.forEach((adminId) => {
      this.devLogsService.fetchGitUser(adminId).then((userData) => {
        this.gitUsers[adminId] = userData;
      });
    });
  }

  deleteLog(id: number): void {
    console.log("Deleting log with ID: ", id);

    if(confirm('Are you sure you want to delete this log?')) {
      this.devLogsService.deleteLog(id).then(response => {
        console.log('Log deleted: ', response);

      }).catch(error => {
        console.error('Error occured in the deleting process: ', error);
      })
    }
  }
}
