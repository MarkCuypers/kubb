define(["dojo/_base/declare", "dijit/_WidgetBase", "dijit/_TemplatedMixin",
    "dojo/text!./AppWidget.html", "dojo/request", "dojo/dom-construct",

    "dojox/mobile/TabBar", "dojox/mobile/TabBarButton", "dojox/mobile/ScrollableView",
    "dojox/mobile/RoundRect"
],
    function (declare, _WidgetBase, _TemplatedMixin, template, request, domConstruct) {
        return declare([_WidgetBase, _TemplatedMixin], {
            templateString: template,
            baseClass: "AppWidget",
            _teamsGrid: null,

            constructor: function (params) {
                this.inherited(arguments);
                console.log(this.baseClass + ": main.constructor");
            },

            postCreate: function () {
                this.inherited(arguments);
                var self = this;
                console.log(this.baseClass + ": main.postCreate");

                var loadPromise = request("http://localhost:8080/kubb/api/teams", {
                    method: "GET",
                    handleAs: "json",
                    withCredentials: false,
                    headers: {
                        "Accept": "application/json",
                        "Access-Control-Allow-Origin": "*",
                        "Content-Type": "json"
                    }
                });
                loadPromise.then(function (data) {
                    if (data) {
                        console.info("data found!");
                        var ulNode = domConstruct.create("ul");
                        domConstruct.place(ulNode, self._teamsGrid);
                        data.forEach(function (team) {
                            var liNode = domConstruct.create("li");
                            liNode.innerHTML = team.name;
                            domConstruct.place(liNode, ulNode);
                        })
                    }
                    else {
                        console.error("No data found");
                    }
                }, function (err) {
                    console.error("Error: " + err);
                }, function (evt) {

                });
            },

            startup: function () {
                this.inherited(arguments);
                console.log(this.baseClass + ": main.startup");
            }
        });
    });
