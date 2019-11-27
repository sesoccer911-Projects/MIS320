/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*jslint  browser: true, white: true, plusplus: true */
        /*global $, films, Packages, Actors */
        $(function () {
            'use strict';
            var filmArray = $.map(films, function (value, key) {
                return {value: value, data: key};
            });
            var actorArray = $.map(Actors, function (value, key) {
                return {value: value, data: key};
            });
            // Initialize ajax autocomplete:
            $('#autocomplete').autocomplete({
                lookup: filmArray,
                lookupLimit: 20,
                minChars: 2,
                onSelect: function (suggestion) {
                    $('#selection-ajax').value(suggestion.data);
                }});
            $('#actor-autocomplete').autocomplete({
                lookup: actorArray,
                lookupLimit: 20,
                minChars: 2,
                onSelect: function (suggestion) {
                    $('#selection-ajax').value(suggestion.data);
                }});
        });
