import {
  AfterViewInit,
  Component
} from '@angular/core';

import { EditorState } from '@codemirror/state';

import { EditorView, lineNumbers } from '@codemirror/view';

import { java } from '@codemirror/lang-java';

import { oneDark } from '@codemirror/theme-one-dark';

@Component({
  selector: 'app-code-editor',
  templateUrl: './code-editor.component.html',
  styleUrls: ['./code-editor.component.css']
})
export class CodeEditorComponent implements AfterViewInit {

output = `Welcome to KTSession Playground

Click Run to execute your Java code.

Ready...`;
  editor!: EditorView;

  ngAfterViewInit(): void {

    const state = EditorState.create({

      doc: `public class Main {

    public static void main(String[] args){

        System.out.println("Welcome to KTSession!");

    }

}`,

      extensions: [

        lineNumbers(),

        java(),

        oneDark

      ]

    });

    this.editor = new EditorView({

      state,

      parent: document.getElementById('editor')!

    });

  }

}