import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateItemComponent } from './item/create-item/create-item.component';
import { ItemListComponent } from './item/item-list/item-list.component';
import { UpdateItemComponent } from './item/update-item/update-item.component';

const routes: Routes = [
  { path: '', redirectTo: 'employee', pathMatch: 'full' },
  {path: 'additem',component: CreateItemComponent },
  {path: 'itemslist/:status', component: ItemListComponent},
  {path: 'updateitem/:id', component: UpdateItemComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
