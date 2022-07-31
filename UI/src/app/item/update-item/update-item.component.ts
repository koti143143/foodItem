import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { data } from 'jquery';
import { ApiResponse } from 'src/app/model/api.response';
import { Item } from 'src/app/model/item.model';
import { ItemService } from 'src/app/service/item.service';

@Component({
  selector: 'app-update-item',
  templateUrl: './update-item.component.html',
  styleUrls: ['./update-item.component.css']
})
export class UpdateItemComponent implements OnInit {

  id: number;
  item :Item;
  apiResonse :ApiResponse;

  constructor(private itemService: ItemService,
    private router : Router,
    private route:ActivatedRoute ) { }

  ngOnInit(): void {
    this.item = new Item();
    this.id = this.route.snapshot.params['id'];
    this.itemService.getItemById(this.id)
    .subscribe(data =>{
      console.log(data);
      this.item = data;
    },error => console.log(error));
  }

  onSubmit() {
    this.itemService.updateItem(this.id, this.item)
    .subscribe(data => {
      console.log(data)
      this.item = new Item();
      this.router.navigate(['/itemslist', 'modify']);
    }, error => console.log(error));
  }

  list() {
    this.router.navigate(['/itemslist','load']);
  }

}
