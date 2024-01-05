import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { MatSort, Sort } from '@angular/material/sort';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogTitle,
  MatDialogContent,
} from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { httpClientService } from 'src/httpClientService';

let ELEMENT_DATA: any[] = [];


@Component({
  selector: 'app-benevoles-table',
  templateUrl: './benevoles-table.component.html',
  styleUrls: ['./benevoles-table.component.css']
})
export class BenevolesTableComponent implements OnInit {
  displayedColumns: string[] = ['id', 'Name', 'Capacity', 'Status'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
  filterValue: string = '';
  clickedRow: any;
  clickedRowId: string = '';
  ready: boolean = false;
  data: any;
  constructor(private _liveAnnouncer: LiveAnnouncer, public dialog: MatDialog, private _snackBar: MatSnackBar, private httpClient : httpClientService) {}

  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  ngAfterViewInit() {
    if (this.ready) {
      this.dataSource.sort = this.sort;
    }
  }
  onClickRow(row: any) {
    this.clickedRow = row;
    this.clickedRowId = row.id;
    console.log(row);
    this.filterValue = row.Capacity;
  }
  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  async ngOnInit() {
    this.httpClient.getAllAides().subscribe((data) => {
      this.data = data;
      console.log(this.data);
      for (let i = 0; i < this.data.length; i++) {
        let element = this.data[i];
        ELEMENT_DATA.push(element);
      }
      this.dataSource = new MatTableDataSource(ELEMENT_DATA);
      this.dataSource.sort = this.sort;
    }
    );
    this.displayedColumns = ['Day', 'Time', 'Capacity', 'Type'];
    this.ready = true;
  }

}
