import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DataTablesModule } from 'angular-datatables';
import { CreateItemComponent } from './item/create-item/create-item.component';
import { ItemListComponent } from './item/item-list/item-list.component';
import { UpdateItemComponent } from './item/update-item/update-item.component';
import { ItemService } from './service/item.service';
@NgModule({
  declarations: [
    AppComponent,
    CreateItemComponent,
    ItemListComponent,
    UpdateItemComponent,
    

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    DataTablesModule
  ],
  providers: [ItemService],
  bootstrap: [AppComponent]
})
export class AppModule { }
