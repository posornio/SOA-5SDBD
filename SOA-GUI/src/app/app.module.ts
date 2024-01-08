import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BenevolesTableComponent } from './benevoles-table/benevoles-table.component';
import {MatRadioModule} from '@angular/material/radio';
import { FormsModule } from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';
import { AidesTableComponent } from './aides-table/aides-table.component';
import { ValideursTableComponent } from './valideurs-table/valideurs-table.component';
import { BeneficiairesTableComponent } from './beneficiaires-table/beneficiaires-table.component';
import { AvisTableComponent } from './avis-table/avis-table.component';

@NgModule({
  declarations: [
    AppComponent,
    BenevolesTableComponent,
    AidesTableComponent,
    ValideursTableComponent,
    BeneficiairesTableComponent,
    AvisTableComponent

  ],
  imports: [BrowserModule,HttpClientModule,AppRoutingModule,MatRadioModule,MatTableModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
