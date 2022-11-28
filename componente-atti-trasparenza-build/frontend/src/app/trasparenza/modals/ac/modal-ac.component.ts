import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-ac-bmodal',
  templateUrl: './modal-ac.component.html',
  styleUrls: ['./modal-ac.component.scss']
})
export class DettaglioAcBModalComponent implements OnInit, OnDestroy {
  @Input() content: any;

  constructor(public activeModal: NgbActiveModal,
  			  public translateService: TranslateService) {}

  ngOnInit() {
  }
  
  ngOnDestroy() {
  }
}
