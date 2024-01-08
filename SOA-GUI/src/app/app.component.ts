import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'SOA-GUI';
  favoriteSeason: string = 'Benevoles';
  seasons: string[] = ['Benevoles', 'Beneficiaires', 'Valideurs', 'Aides','Avis'];
}
