import { Component, OnInit } from '@angular/core';
import { FooterComponent } from '../footer/footer.component';
import { DevLogsService } from '../../_services/dev-logs.service';
import { CommonModule } from '@angular/common';
import { LoggedinnavbarComponent } from "../loggedinnavbar/loggedinnavbar.component";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admindevlog',
  standalone: true,
  imports: [FooterComponent, CommonModule, LoggedinnavbarComponent, FormsModule],
  templateUrl: './admindevlog.component.html',
  styleUrl: './admindevlog.component.css'
})
export class AdmindevlogComponent implements OnInit{
  logList: any[] = [];
  newLog = { adminId: 0, log: ''};
  showModal = false;
  showAllLogs = false;

  constructor(private devLogsService: DevLogsService) {}

  ngOnInit(): void {
    this.devLogsService.fetchLogsData().then((data) => {
      this.logList = data;
    });

    const adminId = localStorage.getItem('userId');
    if(adminId) {
      this.newLog.adminId = parseInt(adminId, 10);
    } else {
      console.error('No adminId found in localStorage');
    }
  }

  openCreateLogModal() {
    this.showModal = true;
  }

  closeCreateLogModal() {
    this.showModal = false;
  }

  submitLog() {
    if(this.newLog.adminId == null) {
      console.error('Admin ID is not available');
      return;
    }

    this.devLogsService.createLog(
      this.newLog.log,
      this.newLog.adminId
    ).then(response => {
      console.log("Log created successfully", response);
      window.location.reload();
      this.closeCreateLogModal();
    }).catch(error => {
      console.error("Error creating log", error);
    });
  }

  isFormValid(): boolean {
    return (
      this.newLog.log.trim().length > 0 &&
      this.newLog.adminId > 0
    );
  }

  toggleLogs() {
    this.showAllLogs = !this.showAllLogs;
  }

  deleteLog(id: number): void {
    console.log("Deleting log with ID: ", id);

    if(confirm('Are you sure you want to delete this log?')) {
      this.devLogsService.deleteLog(id).then(response => {
        console.log('Log deleted: ', response);
        window.location.reload();
      }).catch(error => {
        console.error('Error occured in the deleting process: ', error);
      })
    }
  }
}
