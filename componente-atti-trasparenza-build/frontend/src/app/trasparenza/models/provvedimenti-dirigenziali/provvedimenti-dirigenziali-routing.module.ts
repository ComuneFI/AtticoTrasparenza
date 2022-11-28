import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Route, extract } from '@app/core';
import { ProvvedimentiDirigenzialiComponent } from './provvedimenti-dirigenziali.component';

const routes: Routes = [
  Route.withShell([
    { path: 'provvedimenti-dirigenziali', component: ProvvedimentiDirigenzialiComponent, data: { title: extract('managerialMeasures') } }
  ])
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class ProvvedimentiDirigenzialiRoutingModule { }
