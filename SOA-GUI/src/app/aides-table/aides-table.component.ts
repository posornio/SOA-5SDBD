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
  selector: 'app-aides-table',
  templateUrl: './aides-table.component.html',
  styleUrl: './aides-table.component.css'
})
export class AidesTableComponent {
  add = false;
  valider = false;
  refuse = false;
  affecter = false;
  newType = '';
  newNom = '';
  affectTo = 0;
  valideur = 0;
  displayedColumns: string[] = ['id','Beneficiaire','Type','Status', 'Motif de rejet', 'Benevole associé', 'Traitée par'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
  filterValue: string = '';
  clickedRow: any;
  benevoles : any[] = [];
  beneficiaires : any[] = [];
  valideurs : any[] = [];
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
    ELEMENT_DATA = [];
    this.dataSource = new MatTableDataSource(ELEMENT_DATA);
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
    this.getAllBenevoles();
    this.getAllBeneficiaires();
    this.getAllValideurs();
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
onSelectValider() {
  this.valider = !this.valider;
}

onSelectRefuser() {
  this.refuse = !this.refuse;
}

onClickValiderConfirm() {
  if (this.clickedRow) {
    let q = this.httpClient.validerAide(this.clickedRow.id,this.valideur).subscribe(
      (response) => {
        console.log('Aide validée successfully:', response);
        // Handle success, if needed
      },
      (error) => {
        console.error('Error validating Aide:', error);
        // Handle error, if needed
      }
    );
    this.ngOnInit();
  }
  this.valider = false;
  ELEMENT_DATA = [];
  this.ngOnInit();

}

onClickRefuserConfirm() {
  if (this.clickedRow) {
    let q = this.httpClient.refuserAide(this.clickedRow.id,this.valideur,this.newType).subscribe(
      (response) => {
        console.log('Aide refusée successfully:', response);
        // Handle success, if needed
      },
      (error) => {
        console.error('Error refusing Aide:', error);
        // Handle error, if needed
      }
    );
    this.ngOnInit();
    this._snackBar.open('Aide refusée', 'Fermer', {
      duration: 2000,
    });
  }
  this.refuse = false;
  this.ngOnInit();

}


onSelectAffecter() {
  this.affecter = !this.affecter;
}

onClickAffecterConfirm() {
  if (this.clickedRow) {
    let q = this.httpClient.affecterAide(this.clickedRow.id,this.valideur,this.affectTo).subscribe(
      (response) => {
        console.log('Aide affectée successfully:', response);
        // Handle success, if needed
      },
      (error) => {
        console.error('Error affecting Aide:', error);
        // Handle error, if needed
      }
    );
    this.ngOnInit();
    this._snackBar.open('Aide affectée', 'Fermer', {
      duration: 2000,
    });
  }
  this.affecter = false;
  this.ngOnInit();


}

}