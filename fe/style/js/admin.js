function appendItem() {
  $("#list_append_item").append('<div class="border_control append_item"><div style="width: 37%" class="group_item"><label class="sub_label">날짜 </label><div class="sub_input"> <input name="test138" type="text" data-date-format="yyyy/mm/dd" value="2016/08/23" class="form-control input-small date-picker"/> </div> </div> <div class="group_item"> <label class="sub_label">시간 </label> <div class="sub_input"> <div class="item_time"> <input type="text" name="from" style="width: 100px;" class="form-control"/> </div> <div class="item_time"> <span class="input-group-addon">~ </span></div> <div class="item_time"> <input type="text" name="from" style="width: 100px;" class="form-control"/> </div> </div> </div>  <div class="trash group_item"> <a href="javascript:;" class="trash_icon detele_item"> <i class="fa fa-remove"></i></a></div> </div>');     // Append new elements
}
$(document).ready(function() {
  // $(this).attr("href");

  (function($) {
    function brelink() {
      if ($('.side-menu .current-page').length) {
        var titlefirst = $('.side-menu li.active > a').text();
        var titlelast = $('.side-menu .current-page a').text();
        var titleAddition =  $(".page-breadcrumb").html();
        var pram = window.location.pathname;
        console.log("titlefirst: "+titlefirst);
        console.log("titlelast: "+titlelast);
        console.log("titleAddition: "+titleAddition);
        if (pram =='/auth/user/member/create') {
        	titlelast = "후원회원 등록";
		}

        $(".page-breadcrumb").empty();
        $(".page-breadcrumb").append('<li><a href="/auth/campaign" class="home"> <i class="fa fa-home"></i><span>홈</span></a></li>');
        if(titlefirst != ''){
        	$(".page-breadcrumb").append('<li class="menu-lever1"><a>'+(titlefirst)+'</a></li>');
        }
        if(titlelast != ''){
        	$(".page-breadcrumb").append('<li class="menu-lever2"><a>'+(titlelast)+'</a></li>');
        }
        $(".page-breadcrumb").append(titleAddition);
        // $("body").addClass('fix-addtocat');
      }
    }

    $(window).on('load', function() {
      brelink();
    });
  })(jQuery);

  $('.last-checkbox').change(function(){
    if($('.last-checkbox').is(":checked")) {
      $('.list-sub-checkbox').show(300);
    }
    else{
      $('.list-sub-checkbox').hide(300);
    }
  });

  $(document.body).on('click', '.detele_item', function() {
    $(this).closest('.append_item').fadeOut(300, function() {
      $(this).remove();
    });
  });/* --------------------------------------------------------------------- */
  /* Delete item
  /* --------------------------------------------------------------------- */

  $(document.body).on('click', '.btn_delete', function() {
    $(this).closest('.item-comment').fadeOut(300, function() {
      $(this).remove();
    });
  });

  /* --------------------------------------------------------------------- */
  /* Metronic
  /* --------------------------------------------------------------------- */



  /* --------------------------------------------------------------------- */
  /* data-rw-placeholder
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$("[data-rw-placeholder]").length) return;

    $("[data-rw-placeholder]").each(function() {
      var placeholderContent = $(this).attr("data-rw-placeholder");
      $(this).attr("placeholder", placeholderContent);

      $(this).on("focus", function() {
        $(this).attr("placeholder", "");
      });

      $(this).on("blur", function() {
        $(this).attr("placeholder", placeholderContent);
      });
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .date-picker
  /* --------------------------------------------------------------------- */
  (function($) {
    // if (!$(".date-picker").length) return;
    // $('.date-picker').datepicker({
    //   orientation: "left",
    //   autoclose: true,
    //   language: 'ja'
    // });
    //   $('input[name="daterange"]').daterangepicker({
    //   opens: 'left',
    //   autoclose: true,
    //   locale: 'ja',
    //   language: 'ja'
    // }, function(start, end, label) {
    //   console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
    // });
//     $('input[name="daterange"]').daterangepicker({
//       "locale": {
//           "format": "YYYY-MM-DD",
//           "separator": " - ",
//           "applyLabel": "Aplicar",
//           "cancelLabel": "Cancelar",
//           "customRangeLabel": "Custom",
//           "daysOfWeek": [
//               "土",
//               "日",
//               "月",
//               "火",
//               "水",
//               "木",
//               "金",
//           ],
//           "monthNames": [
//               "1月",
//               "2月",
//               "3月",
//               "4月",
//               "5月",
//               "6月",
//               "7月",
//               "8月",
//               "9月",
//               "10月",
//               "11月",
//               "12月"
//           ],
//           "firstDay": 1
//       }
// });
  })(jQuery);




  /* --------------------------------------------------------------------- */
  /* .ckeditor
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".ckeditor").length) return;

    var idNum = 1;

    $(".ckeditor").each(function() {
      $(this).attr("id", "sb-ckeditor" + idNum);
      var id = $(this).attr("id");

      $("" + id + "").ckeditor({
        language: "ko"
      });

      idNum++;
    });

  })(jQuery);


  /* --------------------------------------------------------------------- */
  /* .check-all-wrapper
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".check-all-wrapper").length) return;

    $(document.body).on("change", ".check-all", function() {
      var checkAll = $(this);
      var wrapper = $(this).closest(".check-all-wrapper");
      var item = wrapper.find(".check-item");

      checkAll.prop('checked') == true ? item.prop('checked', true) : item.prop('checked', false)

      $.uniform.update();
    });

    $(document.body).on("change", ".check-item", function() {
      var item = $(this);
      var wrapper = $(this).closest(".check-all-wrapper");
      var checkAll = wrapper.find(".check-all");
      var countItem = wrapper.find(".check-item:not(':checked')").length;

      countItem === 0 ? checkAll.prop('checked', true) : checkAll.prop('checked', false)

      $.uniform.update();
    });

  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .check-all-wrapper
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".rw-toggle-checkbox").length) return;

    $(".rw-toggle-checkbox").each(function() {
      var checkbox = $(this).find(".toggle-checkbox");
      var content = $(this).find(".toggle-content");

      checkbox.prop('checked') == true ? content.show() : content.hide()
    });

    $(document.body).on("change", ".toggle-checkbox", function() {
      var checkbox = $(this);
      var wrapper = $(this).closest(".rw-toggle-checkbox");
      var content = wrapper.find(".toggle-content");

      checkbox.prop('checked') == true ? content.show() : content.hide()

      $.uniform.update();
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* .sortable
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$(".sortable").length) return;

    $(".sortable").each(function() {
      $(this).sortable({
        cursor: "move",
        update: function(event, ui) {
          var changedList = this.id;
          var order = $(this).sortable('toArray');
          var positions = order.join(';');

          alert("PLEASE VIEW CONSOLE.LOG");

          console.log({
            id: changedList,
            positions: positions
          });
        }
      });

      $(this).disableSelection();
    });
  })(jQuery);



  /* --------------------------------------------------------------------- */
  /* Create preview image upload
  /* --------------------------------------------------------------------- */
  // (function() {
  //   if (!$('.rw-file-upload.image-upload').length) return;

  //   $('.rw-file-upload.image-upload').each(function() {
  //     var fileUpload = $(this);

  //     var readURL = function(input) {
  //       var previewImage = fileUpload.find(".preview-image img");

  //       if (input.files && input.files[0]) {
  //         var reader = new FileReader();

  //         reader.onload = function(e) {
  //           previewImage.attr('src', e.target.result);
  //         }

  //         reader.readAsDataURL(input.files[0]);
  //       }
  //     };

  //     fileUpload.find(".input-file").on("change", function() {
  //       readURL(this);
  //     });
  //   });
  // })();

  /* --------------------------------------------------------------------- */
  /* Create preview image upload
  /* --------------------------------------------------------------------- */
  // (function($) {
  //   function inputuploadimg() {
  //       if (!$('.tr-file-upload.image-upload').length) return;

  //       $('.tr-file-upload.image-upload').each(function() {
  //         var fileUpload = $(this);
  //         var previewImage = fileUpload.find('.preview-image > img');
  //         var previewImageBg = fileUpload.find('.preview-image > .image-bg');


  //         previewImage.attr('src') == '' ? previewImage.closest('.preview-image').addClass('no-image') : previewImage.closest('.preview-image').removeClass('no-image');
  //         previewImageBg.attr('style') == undefined ? previewImageBg.closest('.preview-image').addClass('no-image') : previewImageBg.closest('.preview-image').removeClass('no-image');

  //         var readURL = function(input) {
  //           if (input.files && input.files[0]) {
  //             var reader = new FileReader();

  //             reader.onload = function(e) {

  //               previewImage.attr('src', e.target.result);
  //               previewImageBg.attr('style', 'background-image: url("' + e.target.result + '");');
  //             }

  //             reader.readAsDataURL(input.files[0]);
  //           }
  //         };

  //         fileUpload.find('.input-file').on('change', function() {
  //           readURL(this);
  //         });
  //       });
  //   }

  //   $(window).on('load', function() {
  //     inputuploadimg();
  //   });
  // })(jQuery);

  // all upload file
  // (function($) {
  //   function inputuploadall() {
  //       if (!$('.all-file-upload.image-upload').length) return;

  //       $('.tr-file-upload.image-upload').each(function() {
  //         var fileUpload = $(this);
  //         // var previewImage = fileUpload.find('.preview-image > img');
  //         // var previewImageBg = fileUpload.find('.preview-image > .image-bg');


  //         // previewImage.attr('src') == '' ? previewImage.closest('.preview-image').addClass('no-image') : previewImage.closest('.preview-image').removeClass('no-image');
  //         // previewImageBg.attr('style') == undefined ? previewImageBg.closest('.preview-image').addClass('no-image') : previewImageBg.closest('.preview-image').removeClass('no-image');

  //         var readURL = function(input) {
  //           if (input.files && input.files[0]) {
  //             var reader = new FileReader();

  //             reader.onload = function(e) {

  //               // previewImage.attr('src', e.target.result);
  //               // previewImageBg.attr('style', 'background-image: url("' + e.target.result + '");');
  //             }

  //             reader.readAsDataURL(input.files[0]);
  //           }
  //         };

  //         fileUpload.find('.input-file').on('change', function() {
  //           readURL(this);
  //         });
  //       });
  //   }

  //   $(window).on('load', function() {
  //     inputuploadall();
  //   });
  // })(jQuery);

  /* --------------------------------------------------------------------- */
  /* Check valid file upload
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$('.all-file-upload').length) return;

    var popupUploadAgain = $('#popupUploadAgain');

    $('.all-file-upload').each(function() {
      var fileUploadWrapper = $(this);
      var inputFile = fileUploadWrapper.find('.input-file');
      var borderBox = fileUploadWrapper.find('.border-box');
      var showFileInfo = fileUploadWrapper.find('.show-file-info');
      // var previewImage = fileUploadWrapper.find('.preview-image');
      var deleteButton = fileUploadWrapper.find('.btn-delete');

      inputFile.on('change', function() {
        var thisInputFile = $(this);
        var file = thisInputFile[0].files[0];
        var fileName = file.name;
        var fileSize = file.size;
        showFileInfo.empty().text(fileName);

        if ($('.tr-file-upload.image-upload').length) {
          var capacityLimit = 3 * 1024 * 1024; // = 21MB
          var fileExtension = fileName.split('.').pop().toLowerCase();
          // var listExtensionArray = ['jpg', 'gif', 'png'];
          // var inArrayExtension = $.inArray(fileExtension, listExtensionArray);
          // if(inArrayExtension >= 0){

          // }else{
          //   // alert('파일 형식이 맞지 않습니다.');
          //   // exit();
          // }

          if (fileSize / capacityLimit <= 1) {
            thisInputFile.removeClass('input-empty').addClass('input-not-empty');
            borderBox.css('border', '');
            // previewImage.removeClass('no-image');
          } else {
            // alert('이미지는 3MB 이하만 등록 가능합니다.');
            // exit();
            // thisInputFile.replaceWith(thisInputFile = thisInputFile.clone(true));
            // thisInputFile.removeClass('input-not-empty').addClass('input-empty');
            // borderBox.css('border', '1px solid #ff0000');
            // previewImage.addClass('no-image');
            // showFileInfo.empty();
            // popupUploadAgain.modal('show');
          }
        }

        if (fileUploadWrapper.find('.input-file').val() === '') {
          thisInputFile.addClass('input-empty').removeClass('input-not-empty');
        } else {
          thisInputFile.addClass('input-not-empty').removeClass('input-empty');
        }
      });

      deleteButton.on('click', function() {
        inputFile.replaceWith(inputFile = inputFile.clone(true));
        inputFile.removeClass('input-not-empty').addClass('input-empty');
        borderBox.css('border', '');
        previewImage.addClass('no-image').find('> img').attr('src', '');
        previewImage.addClass('no-image').find('> .image-bg').attr('style', 'background-image: url();');
        showFileInfo.empty();
      });
    });
  })(jQuery);

  /* --------------------------------------------------------------------- */
  /* Create preview image upload
  /* --------------------------------------------------------------------- */
  // (function($) {
  //   function inputuploadimg() {
  //       if (!$('.tr-file-upload.image-upload').length) return;

  //       $('.tr-file-upload.image-upload').each(function() {
  //         var fileUpload = $(this);
  //         var previewImage = fileUpload.find('.preview-image > img');
  //         var previewImageBg = fileUpload.find('.preview-image > .image-bg');


  //         previewImage.attr('src') == '' ? previewImage.closest('.preview-image').addClass('no-image') : previewImage.closest('.preview-image').removeClass('no-image');
  //         previewImageBg.attr('style') == undefined ? previewImageBg.closest('.preview-image').addClass('no-image') : previewImageBg.closest('.preview-image').removeClass('no-image');

  //         var readURL = function(input) {
  //           if (input.files && input.files[0]) {
  //             var reader = new FileReader();

  //             reader.onload = function(e) {

  //               previewImage.attr('src', e.target.result);
  //               previewImageBg.attr('style', 'background-image: url("' + e.target.result + '");');
  //             }

  //             reader.readAsDataURL(input.files[0]);
  //           }
  //         };

  //         fileUpload.find('.input-file').on('change', function() {
  //           readURL(this);
  //         });
  //       });
  //   }

  //   $(window).on('load', function() {
  //     inputuploadimg();
  //   });
  // })(jQuery);

  /* --------------------------------------------------------------------- */
  /* Check valid file upload
  /* --------------------------------------------------------------------- */
  (function($) {
    if (!$('.tr-file-upload').length) return;

    var popupUploadAgain = $('#popupUploadAgain');

    $('.tr-file-upload').each(function() {
      var fileUploadWrapper = $(this);
      var inputFile = fileUploadWrapper.find('.input-file');
      var borderBox = fileUploadWrapper.find('.border-box');
      var showFileInfo = fileUploadWrapper.find('.show-file-info');
      var previewImage = fileUploadWrapper.find('.preview-image');
      var deleteButton = fileUploadWrapper.find('.btn-delete');

      inputFile.on('change', function() {
        var thisInputFile = $(this);
        var file = thisInputFile[0].files[0];
        var fileName = file.name;
        var fileSize = file.size;
        showFileInfo.empty().text(fileName);

        if ($('.tr-file-upload.image-upload').length) {
          var capacityLimit = 3 * 1024 * 1024; // = 21MB
          var fileExtension = fileName.split('.').pop().toLowerCase();
          var listExtensionArray = ['jpg', 'gif', 'png'];
          var inArrayExtension = $.inArray(fileExtension, listExtensionArray);
          if(inArrayExtension >= 0){

          }else{
            alert('ファイル形式が正しくありません。');
            exit();
          }

          if (inArrayExtension >= 0 && fileSize / capacityLimit <= 1) {
            thisInputFile.removeClass('input-empty').addClass('input-not-empty');
            borderBox.css('border', '');
            previewImage.removeClass('no-image');
          } else {
            alert('画像は3MB以下のみ登録可能です。');
            exit();
            thisInputFile.replaceWith(thisInputFile = thisInputFile.clone(true));
            thisInputFile.removeClass('input-not-empty').addClass('input-empty');
            borderBox.css('border', '1px solid #ff0000');
            previewImage.addClass('no-image');
            showFileInfo.empty();
            popupUploadAgain.modal('show');
          }
        }

        if (fileUploadWrapper.find('.input-file').val() === '') {
          thisInputFile.addClass('input-empty').removeClass('input-not-empty');
        } else {
          thisInputFile.addClass('input-not-empty').removeClass('input-empty');
        }
      });

      deleteButton.on('click', function() {
        inputFile.replaceWith(inputFile = inputFile.clone(true));
        inputFile.removeClass('input-not-empty').addClass('input-empty');
        borderBox.css('border', '');
        previewImage.addClass('no-image').find('> img').attr('src', '');
        previewImage.addClass('no-image').find('> .image-bg').attr('style', 'background-image: url();');
        showFileInfo.empty();
      });
    });
  })(jQuery);

  /* --------------------------------------------------------------------- */
  /* Check valid file upload
  /* --------------------------------------------------------------------- */
  // (function() {
  //   if (!$('.rw-file-upload').length) return;

  //   var popupUploadAgain = $('#popupUploadAgain');

  //   $('.rw-file-upload').each(function() {
  //     var fileUploadWrapper = $(this);
  //     var inputFile = fileUploadWrapper.find('.input-file');
  //     var borderBox = fileUploadWrapper.find('.border-box');
  //     var showFileInfo = fileUploadWrapper.find('.show-file-info');
  //     var previewImage = fileUploadWrapper.find('.preview-image');
  //     var deleteButton = fileUploadWrapper.find('.btn-delete');

  //     inputFile.on('change', function() {
  //       var thisInputFile = $(this);
  //       var file = thisInputFile[0].files[0];
  //       var fileName = file.name;
  //       var fileSize = file.size;

  //       showFileInfo.empty().text(fileName);

  //       if ($('.rw-file-upload.image-upload').length) {
  //         var capacityLimit = 21 * 1024 * 1024; // = 8MB
  //         var fileExtension = fileName.split('.').pop().toLowerCase();
  //         var listExtensionArray = ["jpg", "jpeg", "gif", "png"];
  //         var inArrayExtension = $.inArray(fileExtension, listExtensionArray);

  //         if (inArrayExtension >= 0 && fileSize / capacityLimit <= 1) {
  //           thisInputFile.removeClass("input-empty").addClass("input-not-empty");
  //           borderBox.css("border", "");
  //           previewImage.removeClass("no-image");
  //         } else {
  //           thisInputFile.replaceWith(thisInputFile = thisInputFile.clone(true));
  //           thisInputFile.removeClass("input-not-empty").addClass("input-empty");
  //           borderBox.css("border", "1px solid #ff0000");
  //           previewImage.addClass("no-image");
  //           showFileInfo.empty();
  //           popupUploadAgain.modal('show');
  //         }
  //       }

  //       if (fileUploadWrapper.find('.input-file').val() === "") {
  //         thisInputFile.addClass("input-empty").removeClass("input-not-empty");
  //       } else {
  //         thisInputFile.addClass("input-not-empty").removeClass("input-empty");
  //       }
  //     });

  //     deleteButton.on('click', function() {
  //       inputFile.replaceWith(inputFile = inputFile.clone(true));
  //       inputFile.removeClass("input-not-empty").addClass("input-empty");
  //       borderBox.css("border", "");
  //       previewImage.addClass("no-image").find("img").attr("src", "");
  //       showFileInfo.empty();
  //     });
  //   });
  // })();



  /* --------------------------------------------------------------------- */
  /* fullCalendar
  /* --------------------------------------------------------------------- */



  /* --------------------------------------------------------------------- */
  /* validation form_sample_3
  /* --------------------------------------------------------------------- */



  /* --------------------------------------------------------------------- */
  /* fullCalendar
  /* --------------------------------------------------------------------- */

$('.collapse').collapse()

});

