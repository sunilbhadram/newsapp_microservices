import { NgModule } from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button'; 
import {MatListModule} from '@angular/material/list'; 
import {MatIconModule} from '@angular/material/icon'; 
import {MatDividerModule} from '@angular/material/divider'; 
import {MatPaginatorModule} from '@angular/material/paginator'; 
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner'; 
import { MatTooltipModule } from '@angular/material/tooltip';
import {MatSidenavModule } from '@angular/material';
import { FlexLayoutModule } from "@angular/flex-layout";

const materialComponents = [
  MatToolbarModule,MatInputModule,
  MatButtonModule,MatListModule,MatIconModule,MatDividerModule,
  MatPaginatorModule,MatProgressSpinnerModule,MatTooltipModule,MatSidenavModule,FlexLayoutModule
];

@NgModule({
  declarations: [],
  imports: [
    materialComponents
  ],
  exports: [
    materialComponents
  ]
})
export class MaterialModule { }
