import { Component, OnInit } from '@angular/core';
import { ItemListComponent } from '../item-list/item-list.component';
import { Item } from 'src/app/model/item.model';
import { ItemService } from 'src/app/service/item.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {

  item: Item = new Item();
  submitted = false;

  constructor(private itemService: ItemService,
    private router: Router) { }

  ngOnInit() {
  }


  onSubmit() {
    this.submitted = true;
    this.itemService.createItem(this.item)
    .subscribe(data => console.log(data), error => console.log(error));
    this.item = new Item();
    this.router.navigate(['/itemslist','create']);
  }

}
