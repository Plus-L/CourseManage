<template>
  <div class="home">
    <el-container style="border: 1px solid #eee">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246);height: 100%">
        <el-menu :default-openeds="['1']">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>导航一</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>导航二</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>导航三</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>

        <el-header style="text-align: right; font-size: 16px; border-bottom: 1px solid #ccc; line-height: 60px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>韩慧媛</span>
        </el-header>

        <el-main>

          <el-button type="success" @click="handleAdd">新增课程</el-button>

          <el-table :data="tableData" border stripe>
            <el-table-column prop="id" label="编号" width="60">
            </el-table-column>
            <el-table-column prop="name" label="课程名" width="120">
            </el-table-column>
            <el-table-column prop="hours" label="课时">
            </el-table-column>
            <el-table-column prop="sid" label="开课学院">
            </el-table-column>
            <el-table-column label="精品图片" prop="headurl">
                  <el-image src=""></el-image>
            </el-table-column>

            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                <el-popconfirm
                    class="ml-5"
                    confirm-button-text='确定'
                    cancel-button-text='我再想想'
                    icon="el-icon-info"
                    icon-color="red"
                    title="您确定删除吗？"
                    @confirm="deleteStu(scope.row.id)"
                >
                  <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                </el-popconfirm>
                <el-button type="primary" @click="updateHeadUrlDialog">上传图片</el-button>
              </template>
            </el-table-column>
          </el-table>

          <el-dialog title="新增/修改课程" :visible.sync="dialogFormVisible" width="40%">
            <el-form label-width="120px">
              <el-form-item label="课程名称">
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="课时">
                <el-input v-model="form.hours" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="所属学院">
                <el-select v-model="form.sid" placeholder="请选择所属学院">
                  <el-option label="计算机学院" value="1"></el-option>
                  <el-option label="外国语学院" value="2"></el-option>
                </el-select>
              </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </div>
          </el-dialog>

          <el-dialog title="上传图片" :visible.sync="PICdialogFormVisible" width="40%">
            <el-form label-width="120px">
              <el-form-item label="上传图片">
                <div>
                  <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="80px">
                  </el-form>
                  <el-form-item label="图片" prop="chairImg">
                    <el-upload
                        class="avatar-uploader"
                        action="http://localhost:8090/student/upload"
                        :show-file-list="false"
                        multiple
                        :limit="1"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                      <!-- 构建虚拟路径 -->
                      <img v-if="editForm.chairImg" :src=" 'http://localhost:8090/api/file/' + editForm.chairImg" class="avatar">
                      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                  </el-form-item>
                </div>

              </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="PICdialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="updateHeadUrl">确 定</el-button>
            </div>
          </el-dialog>

        </el-main>
      </el-container>
    </el-container>

  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'HomeView',
  data() {
    return {
      tableData:[],
      dialogFormVisible: false,
      PICdialogFormVisible: false,
      form: {},
      name: "",
      sid: "",
      hours: "",
      headurl: "",
      editForm: {}, // 修改信息的表单数据
      icon_url: "https://s2.loli.net/2022/05/06/oasLurkg9wPM8pW.png"
    }
  },
  created() {
    request.get("http://localhost:8090/student/findall/").then(res => {
      console.log(res)
      this.tableData = res
    })
  },
  methods: {
    load() {
      request.get("http://localhost:8090/student/findall/").then(res => {
        console.log(res)
        this.tableData = res
      })
    },
    handleAdd() {
      this.dialogFormVisible=true
      this.form = {}
      this.load()
    },
    save() {
      request.post("http://localhost:8090/student/", this.form).then(res =>{
        if(res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    deleteStu(id) {
        request.delete("http://localhost:8090/student/" + id).then(res => {
          if (res.code === '200') {
            this.$message.success("删除成功")
            this.load()
          } else {
            this.$message.error("删除失败")
          }
        })
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    updateHeadUrl() {
      request.post("http://localhost:8090/student/upload", this.form).then(res =>{
        if(res) {
          this.$message.success("保存成功")
          this.PICdialogFormVisible = false
          this.load()
        } else {
          this.$message.error("上传失败")
        }
      })
    },
    updateHeadUrlDialog() {
      this.PICdialogFormVisible = true
    },
    //处理上传文件
    handleAvatarSuccess(res) {
      // 把图片名给img
      this.editForm.chairImg = res;
    },
    beforeAvatarUpload(file) {
      // 设置限定格式
      const img_mimetype = ['image/jpeg','image/jpg','image/png']
      const isJPG =  img_mimetype.includes(file.type);
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error('上传头像只能是图片格式!');
        return false;
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
        return false;
      }
      return isJPG && isLt2M;
    },
    // 提交修改的表单数据
    async editChairmans() {
      const {data: rese} = await this.$http.post('/chairman/editChairmans', this.editForm);
    },


  }

}
</script>
