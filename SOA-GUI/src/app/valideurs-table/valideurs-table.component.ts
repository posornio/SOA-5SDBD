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
  selector: 'app-valideurs-table',
  templateUrl: './valideurs-table.component.html',
  styleUrl: './valideurs-table.component.css'
})
export class ValideursTableComponent {
  add = false;
  newPrenom = '';
  newNom = '';
  displayedColumns: string[] = ['id', 'Nom', 'Prenom'];
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
    this.httpClient.getAllValideurs().subscribe((data) => {
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
    this.displayedColumns = ['id', 'Nom', 'Prenom'];
    this.ready = true;
  }

  onClickAddConfirm() {
    console.log(this.newNom);
    console.log(this.newPrenom);
    let nom = this.newNom;
    let prenom = this.newPrenom;
    let q = this.httpClient.postBeneficiaire(nom, prenom).subscribe(
      (response) => {
        console.log('Valideur added successfully:', response);
        // Handle success, if needed
      },
      (error) => {
        console.error('Error adding Valideur:', error);
        // Handle error, if needed
      }
    );
    this.ngOnInit();
    
    this.add = false;
  
  }
  onSelectAdd() {
    this.add = !this.add;
  }

  onSelectRemove() {
    if (this.clickedRow) {
      let q = this.httpClient.deleteAide(this.clickedRow.id).subscribe(
        (response) => {
          console.log('Aide deleted successfully:', response);
          // Handle success, if needed
        },
        (error) => {
          console.error('Error deleting Aide:', error);
          // Handle error, if needed
        }
      );
      this.ngOnInit();
      this._snackBar.open('Aide supprimée', 'Fermer', {
        duration: 2000,
      });
    }
  }
}
