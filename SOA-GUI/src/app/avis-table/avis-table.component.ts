import { Component, Input, OnInit, ViewChild } from '@angular/core';
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
  selector: 'app-avis-table',
  templateUrl: './avis-table.component.html',
  styleUrl: './avis-table.component.css'
})
export class AvisTableComponent {
  @Input() tableType: string = 'Avis';
  @Input() id : number = 0;
  add = false;
  valider = false;
  refuse = false;
  affecter = false;
  newType = '';
  newNom = '';
  affectTo = 0;
  valideur = 0;
  displayedColumns: string[] = ['id','Note', 'Commentaire', 'Aide','Benevol'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
  filterValue: string = '';
  clickedRow: any;
  benevoles : any[] = [];
  aides : any[] = [];
  beneficiaires : any[] = [];
  valideurs : any[] = [];
  aide : any;
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
  async onClickRow(row: any) {
    console.log(row);
    this.filterValue = row.Capacity;
    console.log(row.aide_reference);
    this.aide= this.findAide(row.aide_reference);
    console.log(this.aide);
    this.clickedRow = row;
    this.clickedRowId = row.id;
  }
  announceSortChange(sortState: Sort) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }

  async ngOnInit() {
    ELEMENT_DATA = [];
    this.dataSource = new MatTableDataSource(ELEMENT_DATA);
    if (this.tableType == 'Avis') {
      this.httpClient.getAllAvis().subscribe((data) => {
        this.data = data;
        console.log(this.data);
        for (let i = 0; i < this.data.length; i++) {
          let element = this.data[i];
          ELEMENT_DATA.push(element);
        }
        this.dataSource = new MatTableDataSource(ELEMENT_DATA);
        this.dataSource.sort = this.sort;
      });}
      else if (this.tableType == 'Benevoles') {
      this.httpClient.getAvisByBenevole(this.id).subscribe((data) => {
        this.data = data;
        console.log(this.data);
        for (let i = 0; i < this.data.length; i++) {
          let element = this.data[i];
          ELEMENT_DATA.push(element);
        }
        this.dataSource = new MatTableDataSource(ELEMENT_DATA);
        this.dataSource.sort = this.sort;
      });}
      this.getAllBenevoles();
      this.getAllAides();
      this.getAllBeneficiaires();
      this.ready = true;
    }
  
  


  getAllBenevoles() {
    this.httpClient.getAllBenevoles().subscribe((data) => {
      this.benevoles = data;
      })}

  getAllBeneficiaires() {
    this.httpClient.getAllBeneficiaires().subscribe((data) => {
      this.beneficiaires = data;
      })}

  getAllValideurs() {
    this.httpClient.getAllValideurs().subscribe((data) => {
      this.valideurs = data;
      })}

  getAllAides() {
    this.httpClient.getAllAides().subscribe((data) => {
      this.aides = data;
      console.log(data);

      })}


  benevolName(id: number) {
    if (id) {
    let nom = '';
    let prenom = '';
    for (let i = 0; i < this.benevoles.length; i++) {
      if (this.benevoles[i].id == id) {
        nom = this.benevoles[i].nom;
        prenom = this.benevoles[i].prenom;
      }
    }
    let name = nom + ' ' + prenom;
    return name;
  }
  else {
    return '';
  }
}
findAide(id: number) {
  if (id) {
  let aide = '';
  for (let i = 0; i < this.aides.length; i++) {
    if (this.aides[i].id == id) {
      aide = this.aides[i];
    }
  }
  return aide;
}
else {
  return '';
}
}

beneficiaireName(id: number) {
  if (id) {
  let nom = '';
  let prenom = '';
  for (let i = 0; i < this.beneficiaires.length; i++) {
    if (this.beneficiaires[i].id == id) {
      nom = this.beneficiaires[i].nom;
      prenom = this.beneficiaires[i].prenom;
    }
  }
  let name = nom + ' ' + prenom;
  return name;
}
else {
  return '';
}
}

valideurName(id: number) {
  if (id) {
  let nom = '';
  let prenom = '';
  for (let i = 0; i < this.valideurs.length; i++) {
    if (this.valideurs[i].id == id) {
      nom = this.valideurs[i].nom;
      prenom = this.valideurs[i].prenom;
    }
  }
  let name = nom + ' ' + prenom;
  return name;
}
else {
  return '';
}
}


onClickAddConfirm() {
  console.log(this.newNom);
  let nom = this.newNom;
  let prenom = this.newType;
  let q = this.httpClient.postAide(nom, prenom).subscribe(
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
    
  }
}



}
