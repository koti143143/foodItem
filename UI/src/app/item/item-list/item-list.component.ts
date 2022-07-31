import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { data } from 'jquery';
import { Observable } from 'rxjs';
import { ApiResponse } from 'src/app/model/api.response';
import { ItemService } from 'src/app/service/item.service';
import { SearchModel } from 'src/app/model/search.model';
import { Item } from 'src/app/model/item.model';
@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  items: Observable<ApiResponse>;
  status : string;
  searchModel : SearchModel;

  constructor(private itemService :ItemService,
    private router: Router,
    private route: ActivatedRoute) {
      setTimeout(function(){
        $(function(){
          $('#itemtable').DataTable();
      });
      },2000);
     }

  ngOnInit(): void {
    this.searchModel = new SearchModel();
    this.status = this.route.snapshot.params['status'];
    this.items = this.itemService.getItems(this.status);
    setTimeout(function(){
      $(function(){
        $('#itemtable').DataTable();
    });
    },2000);
  }

  deleteItem(id: number) {
    this.searchModel = new SearchModel();
    this.itemService.deleteItem(id)
    .subscribe(
      data => { console.log(data)
    this.items = this.itemService.getItems(this.status);
    },
    error => console.log(error));
  }

  editItem(id: number) {
    this.router.navigate(["updateitem", id]);
  }

  search() {
    this.items = this.itemService.customSearch(this.searchModel);
    console.log(this.items);
  }

}
