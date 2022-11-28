import { Directive, ElementRef, HostListener, EventEmitter, Input, Output } from '@angular/core';

@Directive({
  selector: '[appOnScroll]'
})
export class OnScrollDirective {
  @Output()
  public OnScroll: EventEmitter<any> = new EventEmitter();
  private T_TIME = 0;

  constructor(private _elementRef: ElementRef) {

  }

  @HostListener('document:scroll', ['$event.target'])
  public onScroll(targetElement: any) {
      // const clickedInside = this._elementRef.nativeElement.contains(targetElement);
      const evt: any = {
        targetElement: targetElement
      };

      setTimeout(() => {
        this.OnScroll.emit(evt);
      }, this.T_TIME);
  }
}
